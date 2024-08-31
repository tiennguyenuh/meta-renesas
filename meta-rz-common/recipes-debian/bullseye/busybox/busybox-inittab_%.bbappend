INITTAB_APPEND ??= ""
do_install:append() {
	if [ -n "${INITTAB_APPEND}" ]; then
		echo ${INITTAB_APPEND} >> ${D}${sysconfdir}/inittab
	fi
}
