# poky scarthgap commit: b5f4d8952a7e3ea22672951db470f87c65bc8821
# currently, the kernel version is 5.10, so it do not support the API yet
# we may disable it to enable the build
PACKAGECONFIG:remove = "libmount-mountfd-support"
