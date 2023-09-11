SUMMARY = "Wi-Fi Firmware"
DESCRIPTION = "Wi-Fi Firmware"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://brcmfmac43143.bin;md5sum=2be9d4da43aba729bfa3417829e011df \
	file://brcmfmac43236b.bin;md5sum=f579673b5dc45640c814a9c68abcaf55 \
	file://brcmfmac43430-sdio.AP6212.txt;md5sum=56bd4e83a74244068c07f4045450eff6 \
	file://brcmfmac43430-sdio.bin;md5sum=a4d5e67e5cd0cdeb8ffc83a2b3b30a81 \
	file://brcmfmac43430-sdio.linxdot,r01.txt;md5sum=f82599f594f1927889888f41fea0a84c \
	file://fw_bcm43438a1_mfg.bin;md5sum=1db3c510b09c23914ff234165397d809 \
"

inherit deploy

S = "${WORKDIR}"

do_install () {
    install -d ${D}/lib/firmware/brcm
    install -m 0644 ${S}/brcmfmac43143.bin ${D}/lib/firmware/brcm
    install -m 0644 ${S}/brcmfmac43236b.bin ${D}/lib/firmware/brcm
    install -m 0644 ${S}/brcmfmac43430-sdio.AP6212.txt ${D}/lib/firmware/brcm
    install -m 0644 ${S}/brcmfmac43430-sdio.bin ${D}/lib/firmware/brcm
    install -m 0644 ${S}/brcmfmac43430-sdio.linxdot,r01.txt ${D}/lib/firmware/brcm
    install -m 0644 ${S}/fw_bcm43438a1_mfg.bin ${D}/lib/firmware/brcm
}

do_deploy() {
    mkdir -p "${DEPLOYDIR}/lib/firmware/brcm"
    install -m 0644 "${WORKDIR}/brcmfmac43143.bin" "${DEPLOYDIR}/lib/firmware/brcm"
    install -m 0644 "${WORKDIR}/brcmfmac43236b.bin" "${DEPLOYDIR}/lib/firmware/brcm"
    install -m 0644 "${WORKDIR}/brcmfmac43430-sdio.AP6212.txt" "${DEPLOYDIR}/lib/firmware/brcm"
    install -m 0644 "${WORKDIR}/brcmfmac43430-sdio.bin" "${DEPLOYDIR}/lib/firmware/brcm"
    install -m 0644 "${WORKDIR}/brcmfmac43430-sdio.linxdot,r01.txt" "${DEPLOYDIR}/lib/firmware/brcm"
    install -m 0644 "${WORKDIR}/fw_bcm43438a1_mfg.bin" "${DEPLOYDIR}/lib/firmware/brcm"
}

FILES:${PN} += "/lib/firmware/brcm/brcmfmac43143.bin"
FILES:${PN} += "/lib/firmware/brcm/brcmfmac43236b.bin"
FILES:${PN} += "/lib/firmware/brcm/brcmfmac43430-sdio.AP6212.txt"
FILES:${PN} += "/lib/firmware/brcm/brcmfmac43430-sdio.bin"
FILES:${PN} += "/lib/firmware/brcm/brcmfmac43430-sdio.linxdot,r01.txt"
FILES:${PN} += "/lib/firmware/brcm/fw_bcm43438a1_mfg.bin"
