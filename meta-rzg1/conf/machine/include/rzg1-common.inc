# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"

MACHINEOVERRIDES =. "rzg1:"
MACHINE_FEATURES = "opengles"

KERNEL_IMAGETYPE = "uImage"
KERNEL_EXTRA_ARGS += "LOADADDR=0x40008000"

IMAGE_FSTYPES:append = " tar.bz2 ext4"

# Add variable to Build Configuration in build log
BUILDCFG_VARS:append = " SOC_FAMILY"

#add u-boot
PREFERRED_VERSION_u-boot = "v2013.01.01%"
EXTRA_IMAGEDEPENDS += " u-boot"

# We must disable ptest when can not use GPLv3 License,
# ptest require many package, which have GPLv3 License,
DISTRO_FEATURES:remove += "${@bb.utils.contains("INCOMPATIBLE_LICENSE", "GPLv3", "ptest", "",d)}"

# Match version between native and target sysroot in meta-gplv2 when not using GPLv3.
PREFERRED_VERSION_gdbm-native ?= "${@bb.utils.contains("INCOMPATIBLE_LICENSE", "GPLv3", "1.8.3", "",d)}"

BBMASK:append = " optee-client trusted-firmware-a optee-test"
IMAGE_INSTALL:remove += "optee-client"

# Avoid using these recipes in meta-rz-common, use rzg1 recipes instead
BBMASK:append = " meta-rz-common/recipes-multimedia/mmngr-module meta-rz-common/recipes-multimedia/vspmif-module"
BBMASK:append = " meta-rz-common/recipes-kernel/kernel-module-mmngr meta-rz-common/recipes-kernel/kernel-module-vspm meta-rz-common/recipes-kernel/kernel-module-vspmif"
