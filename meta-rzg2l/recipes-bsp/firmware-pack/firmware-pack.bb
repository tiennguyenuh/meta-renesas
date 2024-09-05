SECTION = "bootloaders"
SUMMARY = "Firmware Packaging"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

require include/rzg2l-optee-config.inc
inherit deploy

DEPENDS = " \
	trusted-firmware-a u-boot \
	${@oe.utils.conditional("ENABLE_SPD_OPTEE", "1", " optee-os", "",d)} \
"
DEPENDS += " bootparameter-native fiptool-native"

S = "${WORKDIR}"

UBOOT_DIR = "${DEPLOY_DIR}/images/${MACHINE}"

do_configure[noexec] = "1"
do_compile[depends] += "u-boot:do_deploy"

do_compile () {

	# Create bl2_bp.bin
	bootparameter ${RECIPE_SYSROOT}/boot/bl2-${MACHINE}.bin bl2_bp.bin
	# Add for eSD boot image
	cp bl2_bp.bin bl2_bp_esd.bin

	cat ${RECIPE_SYSROOT}/boot/bl2-${MACHINE}.bin >> bl2_bp.bin

	# Create fip.bin
	fiptool create --align 16 --soc-fw ${RECIPE_SYSROOT}/boot/bl31-${MACHINE}.bin --nt-fw ${UBOOT_DIR}/u-boot.bin fip.bin

	# Convert to srec
	objcopy -I binary -O srec --adjust-vma=0x00011E00 --srec-forceS3 bl2_bp.bin bl2_bp.srec
	objcopy -I binary -O srec --adjust-vma=0x0000 --srec-forceS3 fip.bin fip.srec

        if [ "${PMIC_SUPPORT}" = "1" ]; then
		bootparameter ${RECIPE_SYSROOT}/boot/bl2-${MACHINE}_pmic.bin bl2_bp_pmic.bin
		# Add for eSD boot image
		cp bl2_bp_pmic.bin bl2_bp_esd_pmic.bin

		cat ${RECIPE_SYSROOT}/boot/bl2-${MACHINE}_pmic.bin >> bl2_bp_pmic.bin
		fiptool create --align 16 --soc-fw ${RECIPE_SYSROOT}/boot/bl31-${MACHINE}_pmic.bin --nt-fw ${UBOOT_DIR}/u-boot.bin fip_pmic.bin
		objcopy -O srec --adjust-vma=0x00011E00 --srec-forceS3 -I binary bl2_bp_pmic.bin bl2_bp_pmic.srec
		objcopy -I binary -O srec --adjust-vma=0x0000 --srec-forceS3 fip_pmic.bin fip_pmic.srec
	fi

	if [ "${ENABLE_SPD_OPTEE}" = "1" ]; then
		fiptool update --align 16 --tos-fw ${STAGING_DIR_HOST}/boot/tee-${MACHINE}.bin fip.bin
		objcopy -I binary -O srec --adjust-vma=0x0000 --srec-forceS3 fip.bin fip.srec

		if [ "${PMIC_SUPPORT}" = "1" ]; then
			fiptool update --align 16 --tos-fw ${STAGING_DIR_HOST}/boot/tee-${MACHINE}.bin fip_pmic.bin
			objcopy -I binary -O srec --adjust-vma=0x0000 --srec-forceS3 fip_pmic.bin fip_pmic.srec
		fi
	fi
}

do_deploy () {
	# Create deploy folder
	install -d ${DEPLOYDIR}/target/images

	# Copy fip images
	install -m 0644 ${S}/bl2_bp.bin ${DEPLOYDIR}/target/images/bl2_bp-${MACHINE}.bin
	install -m 0644 ${S}/bl2_bp.srec ${DEPLOYDIR}/target/images/bl2_bp-${MACHINE}.srec
	install -m 0644 ${S}/fip.bin ${DEPLOYDIR}/target/images/fip-${MACHINE}.bin
	install -m 0644 ${S}/fip.srec ${DEPLOYDIR}/target/images/fip-${MACHINE}.srec
}

addtask deploy before do_build after do_compile
