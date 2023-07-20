deviceTypesCommon = require '@resin.io/device-types/common'
{ networkOptions, commonImg, instructions } = deviceTypesCommon

BOARD_POWERON = 'Power on the device'
BOARD_POWEROFF = 'After flashing is completed the status LED will stop blinking, the ethernet LEDs will turn off and the device will enter Post Provisioning state in the dashboard'

postProvisioningInstructions = [
	BOARD_POWERON
	BOARD_POWEROFF
	instructions.REMOVE_INSTALL_MEDIA
	instructions.BOARD_REPOWER
]

module.exports =
	version: 1
	slug: 'bobcat-rk3566'
	name: 'Bobcat RK3566'
	arch: 'aarch64'
	state: 'released'
	community: true

	stateInstructions:
		postProvisioning: postProvisioningInstructions

	instructions: [
		instructions.ETCHER_SD
		instructions.EJECT_SD
		instructions.FLASHER_WARNING
	].concat(postProvisioningInstructions)

	gettingStartedLink:
		windows: 'https://www.balena.io/docs/learn/getting-started/bobcat-rk3566/nodejs/'
		osx: 'https://www.balena.io/docs/learn/getting-started/bobcat-rk3566/nodejs/'
		linux: 'https://www.balena.io/docs/learn/getting-started/bobcat-rk3566/nodejs/'

	options: [ networkOptions.group ]

	yocto:
		machine: 'bobcat-rk3566'
		image: 'balena-image-flasher'
		fstype: 'balenaos-img'
		version: 'yocto-kirkstone'
		deployArtifact: 'balena-image-flasher-bobcat-rk3566.balenaos-img'
		compressed: true

	configuration:
		config:
			partition:
				primary: 1
			path: '/config.json'

	initialization: commonImg.initialization
