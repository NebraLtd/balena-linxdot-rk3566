# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit local-git

SRCREV = "f39b590ea11da2d897644e42119ba5099b2eefc8"
SRC_URI = " \
	https://github.com/rockchip-linux/kernel.git \
	file://${THISDIR}/files/add-rk3566-bobcat-dts.patch \
	file://${THISDIR}/files/add-defconfig.patch \
"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "4.19"
