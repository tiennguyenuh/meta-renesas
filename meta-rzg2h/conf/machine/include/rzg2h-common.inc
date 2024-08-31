require conf/machine/include/rzg2-common.inc
require include/ecc-control.inc

# Configuration for ARM Trusted Firmware
EXTRA_IMAGEDEPENDS += " optee-os"

MACHINEOVERRIDES =. "rzg2h:"

MACHINE_FEATURES:append = " opengles hwh264dec hwh264enc hwh265dec "
