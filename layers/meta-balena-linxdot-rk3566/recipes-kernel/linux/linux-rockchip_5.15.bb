# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit local-git

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCREV = "115472395b0a9ea522ba0e106d6dfd7a73df8ba6"
SRC_URI = " \
        git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=linux-5.15.y \
        file://${THISDIR}/files/changes.patch \
"

BB_STRICT_CHECKSUM = "0"
KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.15"

do_patch:prepend() {
    rm -f ${S}/arch/arm64/configs/defconfig
}
