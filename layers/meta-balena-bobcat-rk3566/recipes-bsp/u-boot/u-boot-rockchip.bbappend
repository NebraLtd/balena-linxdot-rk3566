UBOOT_KCONFIG_SUPPORT = "1"
inherit resin-u-boot

FILESEXTRAPATHS:append := ":${THISDIR}/files"

DEPENDS += "rkbin bc bc-native"

# rework meta-resin patch whose context is different now in u-boot v2019.4
SRC_URI:remove = "file://resin-specific-env-integration-kconfig.patch"

SRC_URI:append = " \
	file://0003-boot-cmd.patch \
	file://0001-Integrate-with-Balena-u-boot-environment.patch \
"



do_compile:append() {
    tools/mkimage -n rk3568 -T rksd -d ${DEPLOY_DIR_IMAGE}/rkbin/rk3566_ddr_1056MHz_v1.10.bin:u-boot-spl.bin idbloader.bin
}

do_compile[depends] += "rkbin:do_deploy"
