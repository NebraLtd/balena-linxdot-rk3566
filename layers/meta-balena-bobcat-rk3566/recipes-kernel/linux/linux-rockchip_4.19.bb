# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit local-git

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCREV = "f39b590ea11da2d897644e42119ba5099b2eefc8"
SRC_URI = " \
	git://github.com/rockchip-linux/kernel.git;branch=develop-4.19; \
	file://${THISDIR}/files/add-rk3566-bobcat-dts.patch \
	file://${THISDIR}/files/add-defconfig.patch \
	file://${THISDIR}/files/add-motorcomm-driver.patch \
"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "4.19"

do_patch:prepend() {
    rm -f ${S}/arch/arm64/configs/bobcat_rk3566_defconfig
}
