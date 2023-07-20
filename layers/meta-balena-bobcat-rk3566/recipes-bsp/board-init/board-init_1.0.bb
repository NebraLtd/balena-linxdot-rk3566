SUMMARY = "Systemd service to initialize board"
DESCRIPTION = "Systemd service to initialize board"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://board-init.service;md5sum=9f7900638290b9df7f0142442ff0e0a8 \
	file://board-init.sh;md5sum=7fef4941b84079ea71d6557ea7740070 \
"

inherit systemd

do_install() {
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/board-init.service ${D}${systemd_system_unitdir}
	install -d ${D}/usr/sbin
	install -m 0755 ${WORKDIR}/board-init.sh ${D}/usr/sbin
}

FILES:${PN} += "/usr/sbin/board-init.sh"

SYSTEMD_SERVICE:${PN} = "board-init.service"

RDEPENDS:${PN} += "bash"
