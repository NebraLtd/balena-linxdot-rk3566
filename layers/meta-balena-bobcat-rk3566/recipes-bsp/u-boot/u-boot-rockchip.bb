require u-boot-rockchip.inc

DESCRIPTION = "Bobcat RX3566 U-Boot"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
DEPENDS += " bison-native rkbin bc bc-native"
FILESEXTRAPATHS:append := ":${THISDIR}/files"
UBOOT_KCONFIG_SUPPORT = "1"
DEPENDS:append = " dtc-native coreutils-native"

SRC_URI = " \
        git://github.com/ariaboard-com/rockchip_rk3568_uboot.git;protocol=https;branch=rk356x \
        file://0001-fixes.patch \
        file://uboot-precompiled.img \
"

BB_STRICT_CHECKSUM = "0"

SRCREV = "5bf6bf6307cb231bf7ff4e413d54f7cbdba8eb76"

inherit resin-u-boot

S = "${WORKDIR}/git"

export TOOLCHAIN_OPTIONS

do_compile() {
    cd ${S}
    ./make.sh CROSS_COMPILE=${TARGET_PREFIX} --bl31 ${DEPLOY_DIR_IMAGE}/rkbin/rk3568_bl31_v1.42.elf rk3568
    tools/mkimage -n rk3568 -T rksd -d ${DEPLOY_DIR_IMAGE}/rkbin/rk3566_ddr_1056MHz_v1.16.bin:spl/u-boot-spl.bin idbloader.bin
    cp ../uboot-precompiled.img uboot.img
    cp u-boot.bin ../build
    cp idbloader.bin ../build
    cp uboot.img ../build
}

do_install:append() {
    install -d ${D}/boot
    install -c -m 0644 ${B}/idbloader.bin ${B}/uboot.img ${D}/boot
}

do_deploy:append() {
    install ${B}/idbloader.bin ${DEPLOYDIR}
    install ${B}/uboot.img ${DEPLOYDIR}
}

do_compile[depends] += "rkbin:do_deploy"
