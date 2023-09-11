#!/bin/bash

function set_gpio() {
    # $1 - gpio number
    # $2 - value
    if ! [[ -d /sys/class/gpio/gpio$1 ]]; then
        echo $1 > /sys/class/gpio/export
    fi
    echo out > /sys/class/gpio/gpio$1/direction
    echo $2 > /sys/class/gpio/gpio$1/value
}

# Expose serial number via /sys/devices/soc0/serial_number
modprobe sysfssn

# Dervice system S/N from Wi-Fi phy mac address
cat /sys/class/ieee80211/phy0/addresses | md5sum | cut -b 1-8 > /sys/devices/soc0/serial_number

# LoRa concentrator power
set_gpio 23 1

# LoRa concentrator reset1
set_gpio 15 0
