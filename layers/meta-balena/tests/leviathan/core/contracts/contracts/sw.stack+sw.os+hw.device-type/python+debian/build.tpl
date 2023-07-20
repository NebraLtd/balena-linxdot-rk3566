# remove several traces of debian python
RUN apt-get purge -y python.*

# http://bugs.python.org/issue19846
# > At the moment, setting "LANG=C" on a Linux system *fundamentally breaks Python 3*, and that's not OK.
ENV LANG C.UTF-8

# key 63C7CC90: public key "Simon McVittie <smcv@pseudorandom.co.uk>" imported
# key 3372DCFA: public key "Donald Stufft (dstufft) <donald@stufft.io>" imported
RUN gpg --batch --keyserver keyring.debian.org --recv-keys 4DE8FF2A63C7CC90 \
    && gpg --batch --keyserver keyserver.ubuntu.com --recv-key 6E3CBCE93372DCFA \
    && gpg --batch --keyserver keyserver.ubuntu.com --recv-keys 0x52a43a1e4b77b059

ENV PYTHON_VERSION {{sw.stack.version}}

# if this is called "PIP_VERSION", pip explodes with "ValueError: invalid truth value '<VERSION>'"
ENV PYTHON_PIP_VERSION {{sw.stack.assets.pip.version}}

ENV SETUPTOOLS_VERSION {{sw.stack.assets.setuptools.version}}

RUN set -x \
    && curl -SLO "{{sw.stack.assets.bin.url}}" \
    && echo "{{sw.stack.assets.bin.checksum}}  {{sw.stack.assets.bin.name}}" | sha256sum -c - \
    && tar -xzf "{{sw.stack.assets.bin.name}}" --strip-components=1 \
    && rm -rf "{{sw.stack.assets.bin.name}}" \
    && ldconfig \
    && curl -SL "{{sw.stack.assets.getPip.url}}" -o get-pip.py \
    && echo "{{sw.stack.assets.getPip.checksum}} *get-pip.py" | sha256sum -c - \
    && python3 get-pip.py \
        --disable-pip-version-check \
        --no-cache-dir \
        --no-compile \
        "pip==$PYTHON_PIP_VERSION" \
        "setuptools==$SETUPTOOLS_VERSION" \
    && rm -f get-pip.py \
    && find /usr/local \
        \( -type d -a -name test -o -name tests \) \
        -o \( -type f -a -name '*.pyc' -o -name '*.pyo' \) \
        -exec rm -rf '{}' + \
    && cd / \
    && rm -rf /usr/src/python ~/.cache

# install "virtualenv", since the vast majority of users of this image will want it
RUN {{sw.stack.assets.pip.command}} install --no-cache-dir virtualenv

ENV PYTHON_DBUS_VERSION {{sw.stack.assets.dbus.version}}

# install dbus-python dependencies 
RUN apt-get update && apt-get install -y --no-install-recommends \
		libdbus-1-dev \
		libdbus-glib-1-dev \
	&& rm -rf /var/lib/apt/lists/* \
	&& apt-get -y autoremove

# install dbus-python
RUN set -x \
	&& mkdir -p /usr/src/dbus-python \
	&& curl -SL "http://dbus.freedesktop.org/releases/dbus-python/dbus-python-$PYTHON_DBUS_VERSION.tar.gz" -o dbus-python.tar.gz \
	&& curl -SL "http://dbus.freedesktop.org/releases/dbus-python/dbus-python-$PYTHON_DBUS_VERSION.tar.gz.asc" -o dbus-python.tar.gz.asc \
	&& gpg --verify dbus-python.tar.gz.asc \
	&& tar -xzC /usr/src/dbus-python --strip-components=1 -f dbus-python.tar.gz \
	&& rm dbus-python.tar.gz* \
	&& cd /usr/src/dbus-python \
	&& PYTHON_VERSION=$(expr match "$PYTHON_VERSION" '\([0-9]*\.[0-9]*\)') ./configure \
	&& make -j$(nproc) \
	&& make install -j$(nproc) \
	&& cd / \
	&& rm -rf /usr/src/dbus-python

{{import partial="config" combination="sw.stack+sw.os+hw.device-type"}}
