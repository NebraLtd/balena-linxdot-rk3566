SUMMARY = "Systemd service to setup BT for Broadcom Bluetooth chips"
DESCRIPTION = "Load Broadcom Bluetooth Chips Firmware"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://brcm-btfw-load.service;md5sum=b2fd89775455ce1c115db108b1c6dc32 \
	file://bcm43438a1.hcd;md5sum=04d6cea34aadd9741d485f07a3160d23 \
	file://brcm_patchram_plus1;md5sum=a8bc23fd69b5c67d6aed1c69c7602788 \
"

inherit systemd

RDEPENDS:${PN} += "bluez5"

do_install() {
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/brcm-btfw-load.service ${D}${systemd_system_unitdir}
	install -d ${D}/lib/firmware 
	install -m 0644 ${WORKDIR}/bcm43438a1.hcd ${D}/lib/firmware
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/brcm_patchram_plus1 ${D}/usr/bin
}

INSANE_SKIP:${PN} += "ldflags"

FILES:${PN} += "/lib/firmware/bcm43438a1.hcd"
FILES:${PN} += "/usr/bin/brcm_patchram_plus1"

SYSTEMD_SERVICE:${PN} = "brcm-btfw-load.service"
