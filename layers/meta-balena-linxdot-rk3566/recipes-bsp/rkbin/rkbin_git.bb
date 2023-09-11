inherit deploy

DESCRIPTION = "Rockchip binary tools (including WiFi/BT firmware)"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    git://github.com/rockchip-linux/rkbin.git;protocol=https \
"
SRCREV = "d6ccfe401ca84a98ca3b85c12b9554a1a43a166c"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile() {
    true
}

do_deploy () {
    install -d ${DEPLOYDIR}/rkbin
    install -d ${DEPLOYDIR}/rkbin/tools
    install -m 755 ${S}/tools/mkimage ${DEPLOYDIR}/rkbin/tools
    install -m 755 ${S}/bin/rk35/rk3566_ddr_1056MHz_v1.16.bin ${DEPLOYDIR}/rkbin
    install -m 755 ${S}/bin/rk35/rk3568_bl31_v1.42.elf ${DEPLOYDIR}/rkbin
}

addtask deploy before do_build after do_compile
