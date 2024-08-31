# Remove these packages if user chooses to build without GPLv3
# since they do not have other license
RDEPENDS:${PN}:remove = "${@bb.utils.contains('INCOMPATIBLE_LICENSE', 'GPLv3', 'gdbserver gdb', '', d)}"
