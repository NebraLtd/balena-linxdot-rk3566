#!/bin/bash

MAC_ADDR_PREFIX=E87829

function set_gpio() {
    # $1 - gpio number
    # $2 - value
    if ! [[ -d /sys/class/gpio/gpio$1 ]]; then
        echo $1 > /sys/class/gpio/export
    fi
    echo out > /sys/class/gpio/gpio$1/direction
    echo $2 > /sys/class/gpio/gpio$1/value
}

function make_mac_addr() {
    mac_part=$(cat /proc/cpuinfo | grep Serial | tr -d ' ' | cut -d: -f2 | rev | cut -b 1-6 | rev | tr a-f A-F)
    echo ${MAC_ADDR_PREFIX}${mac_part}
}

# Expose serial number via /sys/devices/soc0/serial_number
#modprobe sysfssn

# LoRa concentrator power
#set_gpio 103 1

# GPS power
#set_gpio 112 1

# Red LED
#set_gpio 44 0

# Green LED
#set_gpio 45 0

# Blue LED
#set_gpio 47 0

# Ethernet MAC address
#ifconfig eth0 hw ether $(make_mac_addr)
