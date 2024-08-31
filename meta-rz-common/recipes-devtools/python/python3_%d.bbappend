do_install:append:class-native() {
	ln -fs python3-native/python3 ${D}${bindir}/python
}
