SUMMARY = "Systemd service to setup BT for Broadcom Bluetooth chips"
DESCRIPTION = "Load Broadcom Bluetooth Chips Firmware"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://BCM43430A1.hcd;md5sum=a42e658eb0f6074743e51ba1749a5d34 \
	file://BCM4343A1_001.002.009.1005.1015.hcd;md5sum=db843130419d9cebdbdaab685b09dc1f \
"

inherit systemd

RDEPENDS:${PN} += "bluez5"

do_install() {
	install -d ${D}/lib/firmware/brcm
	install -m 0644 ${WORKDIR}/BCM43430A1.hcd ${D}/lib/firmware/brcm
	install -m 0644 ${WORKDIR}/BCM4343A1_001.002.009.1005.1015.hcd ${D}/lib/firmware/brcm
}

INSANE_SKIP:${PN} += " ldflags already-stripped"

FILES:${PN} += "/lib/firmware/brcm/BCM43430A1.hcd"
FILES:${PN} += "/lib/firmware/brcm/BCM4343A1_001.002.009.1005.1015.hcd"
