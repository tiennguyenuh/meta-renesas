FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	file://gstpbfilter.conf \
	file://0001-playback-Add-source-for-getting-video-filter-from-fi.patch \
	file://0002-gst-plugins-base-Down-rank-of-glimagesink.patch \
"


do_install:append() {
    install -Dm 644 ${WORKDIR}/gstpbfilter.conf ${D}/etc/gstpbfilter.conf
    if [ "${USE_OMX_COMMON}" = "1" ]; then
        sed -i "s/videoconvert/vspmfilter/g" ${D}/etc/gstpbfilter.conf
    fi
}

FILES:${PN}:append = " \
    ${sysconfdir}/gstpbfilter.conf \
"
