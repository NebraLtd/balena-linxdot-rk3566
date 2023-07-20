inherit deploy

DESCRIPTION = "Rockchip binary tools (including WiFi/BT firmware)"

LICENSE = "BINARY"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"
NO_GENERIC_LICENSE[BINARY] = "LICENSE.TXT"

SRC_URI = " \
    git://github.com/armbian/rkbin.git;protocol=https \
"
SRCREV = "16291d03c813ad0be83737c6f835919b3cd4fcf3"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"

do_compile() {
    true
}

do_deploy () {
    install -d ${DEPLOYDIR}/rkbin
    install -d ${DEPLOYDIR}/rkbin/tools
    install -m 755 ${S}/tools/mkimage ${DEPLOYDIR}/rkbin/tools
    install -m 755 ${S}/rk35/rk3566_ddr_1056MHz_v1.10.bin ${DEPLOYDIR}/rkbin
    install -m 755 ${S}/rk35/rk3568_bl31_v1.32.elf ${DEPLOYDIR}/rkbin
}

addtask deploy before do_build after do_compile
