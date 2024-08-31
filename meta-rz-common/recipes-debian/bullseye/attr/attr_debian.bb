SUMMARY = "Utilities for manipulating filesystem extended attributes"
HOMEPAGE = "http://savannah.nongnu.org/projects/attr/"
SECTION = "libs"

DEPENDS = "virtual/libintl"

LICENSE = "LGPL-2.1-or-later & GPL-2.0-or-later"
LICENSE:${PN} = "GPL-2.0-or-later"
LICENSE:lib${BPN} = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=2d0aa14b3fce4694e4f615e30186335f \
                    file://doc/COPYING.LGPL;md5=b8d31f339300bc239d73461d68e77b9c \
                    file://tools/attr.c;endline=17;md5=be0403261f0847e5f43ed5b08d19593c \
                    file://libattr/libattr.c;endline=17;md5=7970f77049f8fa1199fff62a7ab724fb"

inherit debian-package
require recipes-debian/bullseye/sources/attr.inc

inherit autotools gettext update-alternatives

BBCLASSEXTEND = "native nativesdk"

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "setfattr"
ALTERNATIVE_TARGET[setfattr] = "${bindir}/setfattr"

PACKAGES =+ "lib${BPN}"
