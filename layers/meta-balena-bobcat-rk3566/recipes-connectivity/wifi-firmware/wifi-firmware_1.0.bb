SUMMARY = "Wi-Fi Firmware"
DESCRIPTION = "Wi-Fi Firmware"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://fw_bcm43438a1.bin;md5sum=e0e407f4b73d216191e7cafc37c99aa7 \
	file://nvram_ap6212a.txt;md5sum=f82599f594f1927889888f41fea0a84c \
"

inherit deploy

S = "${WORKDIR}"

do_install () {
    install -d ${D}/lib/firmware
    install -m 0644 ${S}/fw_bcm43438a1.bin ${D}/lib/firmware
    install -m 0644 ${S}/nvram_ap6212a.txt ${D}/lib/firmware
}

do_deploy() {
    mkdir -p "${DEPLOYDIR}/lib/firmware"
    install -m 0644 "${WORKDIR}/fw_bcm43438a1.bin" "${DEPLOYDIR}/lib/firmware"
    install -m 0644 "${WORKDIR}/nvram_ap6212a.txt" "${DEPLOYDIR}/lib/firmware"
}

FILES:${PN} += "/lib/firmware/fw_bcm43438a1.bin"
FILES:${PN} += "/lib/firmware/nvram_ap6212a.txt"
