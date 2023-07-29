SUMMARY = "Systemd service to setup BT for Broadcom Bluetooth chips"
DESCRIPTION = "Load Broadcom Bluetooth Chips Firmware"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://brcm-btfw-load.service;md5sum=8187deaf21d2c068b2aa92660cf206a6 \
	file://BCM43438A1.hcd;md5sum=3112c57a24a9ab3ac9cefd90d6500262 \
	file://brcm_patchram_plus1;md5sum=8187deaf21d2c068b2aa92660cf206a6 \
"

inherit systemd

RDEPENDS:${PN} += "bluez5"

do_install() {
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/brcm-btfw-load.service ${D}${systemd_system_unitdir}
	install -d ${D}/vendor/etc/firmware
	install -m 0644 ${WORKDIR}/BCM43438A1.hcd ${D}/vendor/etc/firmware
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/brcm_patchram_plus1 ${D}/usr/bin
}

INSANE_SKIP:${PN} += " ldflags already-stripped"

FILES:${PN} += "/vendor/etc/firmware/BCM43438A1.hcd"
FILES:${PN} += "/usr/bin/brcm_patchram_plus1"

SYSTEMD_SERVICE:${PN} = "brcm-btfw-load.service"
