# Remove these packages if user chooses to build without GPLv3
# since they do not have other license
RDEPENDS:packagegroup-core-buildessential:remove = "${@bb.utils.contains('INCOMPATIBLE_LICENSE', 'GPLv3', 'cpp cpp-symlinks gcc gcc-symlinks binutils binutils-symlinks autoconf g++ g++-symlinks automake', '', d)}"
