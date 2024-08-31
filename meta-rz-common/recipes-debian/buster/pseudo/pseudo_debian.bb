# base recipe: meta/recipes-devtools/pseudo/pseudo_git.bb
# base branch: warrior
# base commit: bf363493fec990eaf7577769f1862d439404bd10

require ${COREBASE}/meta/recipes-devtools/pseudo/pseudo.inc

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=243b725d71bb5df4a1e5920b344b86ad"

inherit debian-package
require recipes-debian/buster/sources/pseudo.inc
FILESPATH:append = ":${COREBASE}/meta/recipes-devtools/pseudo/files:${THISDIR}/pseudo"
DEPENDS += "python3-native"

SRC_URI += " \
	file://0001-configure-Prune-PIE-flags.patch \
	file://fallback-passwd \
	file://fallback-group \
	file://moreretries.patch \
	file://toomanyfiles.patch \
	file://0001-Add-statx.patch \
	file://0001-don-t-renameat2-please.patch \
"
