FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

SRC_URI:remove = "https://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz"

SRC_URI:append = " \
    gitsm://github.com/renesas-rcar/gst-plugins-bad.git;branch=RCAR-GEN3e/1.16.3;name=base;protocol=https \
    file://0001_fix_waylandsink_fullscreen.patch \
    file://0002-waylandsink-Add-set-window-position.patch \
    file://0003-waylandsink-Add-property-out-w-out-h-to-display-expe.patch \
    file://0004-waylandsink-Add-mising-code-for-scale-feature.patch \
    file://0005-gstreamer-waylandsink-disable-subsurface-in-fullscre.patch \
    file://0006-waylandsink-Add-support-for-I420-in-dmabuf.patch \
"

SRCREV_base = "3ef17d3c57e12f9d7536e464656b871a8949fa5b"

DEPENDS += "weston"

S = "${WORKDIR}/git"

do_configure:prepend() {
    cd ${S}
    ./autogen.sh --noconfigure
    cd ${B}
}

PACKAGECONFIG:append = " faac faad"
