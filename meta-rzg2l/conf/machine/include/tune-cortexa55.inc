DEFAULTTUNE ?= "cortexa55"

TUNEVALID[cortexa55] = "Enable Cortex-A55 specific processor optimizations"
TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'cortexa55', ' -mtune=cortex-a55', '', d)}"

require conf/machine/include/arm/arch-armv8-2a.inc

# Little Endian base configs
AVAILTUNES += "cortexa55"
ARMPKGARCH:tune-cortexa55             = "cortexa55"
TUNE_FEATURES:tune-cortexa55          = "${TUNE_FEATURES:tune-aarch64} cortexa55"
PACKAGE_EXTRA_ARCHS:tune-cortexa55    = "${PACKAGE_EXTRA_ARCHS:tune-aarch64} cortexa55"
BASE_LIB:tune-cortexa55               = "lib64"
