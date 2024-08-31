require include/rz-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "kernel-module-fdpm mmngr-user-module"
PN = "fdpm-user-module"
SRC_URI = "file://fdpm.tar.bz2 \
"

S = "${WORKDIR}/fdpm"

sysroot_stage_all:append () {
    # add shared header files
    sysroot_stage_dir ${D}/usr/local/include/ ${SYSROOT_DESTDIR}${includedir}
    sysroot_stage_dir ${D}/usr/local/lib/ ${SYSROOT_DESTDIR}${libdir}
}

do_compile() {
    # Build shared library
    cd ${S}/if
    rm -rf libfdpm.so*
    make all ARCH=arm
    # Copy shared library for reference from other modules
    cp -P ${S}/if/libfdpm.so* ${LIBSHARED}
    cp -rf ${S}/include/*h ${STAGING_INCDIR}
}

do_install() {
    # Create destination folder
    mkdir -p ${D}/usr/local/lib/ ${D}/usr/local/include/

    # Copy shared library
    cp -P ${S}/if/libfdpm.so* ${D}/usr/local/lib
    cp -rf ${S}/include/*h ${D}/usr/local/include/
}

# Append function to clean extract source
do_cleansstate:prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${LIBSHARED}/libfdpm.so*
    rm -f ${STAGING_INCDIR}/fdpm_api.h
    rm -f ${STAGING_INCDIR}/fdpm_drv.h
    rm -f ${STAGING_INCDIR}/fdpm_if_fd.h
    rm -f ${STAGING_INCDIR}/fdpm_if.h
    rm -f ${STAGING_INCDIR}/fdpm_if_par.h
    rm -f ${STAGING_INCDIR}/fdpm_if_priv.h
    rm -f ${STAGING_INCDIR}/fdpm_public.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES:${PN} = " \
    /usr/local/lib/libfdpm.so.* \
"

FILES:${PN}-dev = " \
    /usr/local/lib/libfdpm.so \
    /usr/local/include/*.h \
"

RPROVIDES:${PN} += "fdpm-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP:${PN} += "libdir"
INSANE_SKIP:${PN}-dev += "libdir"

do_configure[noexec] = "1"

python do_package_ipk:prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}
