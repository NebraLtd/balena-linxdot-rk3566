SUMMARY = "Wi-Fi Firmware"
DESCRIPTION = "Wi-Fi Firmware"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://fw_cyw43438.bin;md5sum=052b04f970125c23b9e577604118e7c5 \
	file://nvram_azw372.txt;md5sum=a1c0fa48997222373208374b156d3c3e \
"

inherit deploy

S = "${WORKDIR}"

do_install () {
    install -d ${D}/vendor/etc/firmware
    install -m 0644 ${S}/fw_cyw43438.bin ${D}/vendor/etc/firmware
    install -m 0644 ${S}/nvram_azw372.txt ${D}/vendor/etc/firmware
}

do_deploy() {
    mkdir -p "${DEPLOYDIR}/lib/firmware"
    install -m 0644 "${WORKDIR}/fw_cyw43438.bin" "${DEPLOYDIR}/vendor/etc/firmware"
    install -m 0644 "${WORKDIR}/nvram_azw372.txt" "${DEPLOYDIR}/vendor/etc/firmware"
}

FILES:${PN} += "/vendor/etc/firmware/fw_cyw43438.bin"
FILES:${PN} += "/vendor/etc/firmware/nvram_azw372.txt"
