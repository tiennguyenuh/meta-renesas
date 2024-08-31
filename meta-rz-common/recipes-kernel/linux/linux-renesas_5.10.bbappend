DESCRIPTION = "Linux Kernel for RZ SBC board"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

KERNEL_URL = "git://github.com/Renesas-SST/linux-rz.git;branch=master;protocol=https"
BRANCH = "dunfell/rz-sbc"
SRCREV = "${AUTOREV}"
SRC_URI = "${KERNEL_URL};protocol=https;nocheckout=1;branch=${BRANCH}"

SRC_URI:append = "\
	file://sii.cfg \
	file://laird.cfg \
"

