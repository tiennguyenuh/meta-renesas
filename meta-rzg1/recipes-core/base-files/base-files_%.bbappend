require include/rz-path-common.inc

do_install:append () {
    echo "export LD_LIBRARY_PATH=\"${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}
