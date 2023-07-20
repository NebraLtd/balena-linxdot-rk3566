DESCRIPTION = "Rocktech Bobcat PX30 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
require u-boot-rockchip.inc
DEPENDS += "bison-native"


SRC_URI = " \
    git://github.com/rockchip-linux/u-boot.git;protocol=https;branch=next-dev \
"

SRCREV = "aaca6ffec10380a92367f465196905c72f2b243f"

S = "${WORKDIR}/git"

do_install:append() {
    install -d ${D}/boot
    install -c -m 0644 ${B}/idbloader.bin ${B}/uboot.img ${D}/boot
}

do_deploy:append() {
    install ${B}/idbloader.bin ${DEPLOYDIR}
    install ${B}/uboot.img ${DEPLOYDIR}
}
