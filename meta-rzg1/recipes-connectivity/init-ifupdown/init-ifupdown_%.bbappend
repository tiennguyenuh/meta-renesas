FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " file://interfaces_autoWlan"

do_install:append () {
	install -d ${D}/${sysconfdir}/network
	cp ${S}/interfaces_autoWlan ${D}/${sysconfdir}/network/interfaces
}
