DISTRO_FEATURES:append = " docker"

PACKAGES = " \
    packagegroup-docker \
    "

DOCKER_PKGS = "docker ca-certificates ntpdate kernel-module-nf-conntrack-netlink"
RDEPENDS:packagegroup-docker = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'docker', '${DOCKER_PKGS}', '',d)} \
"
