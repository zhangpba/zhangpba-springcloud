#!/bin/bash
sudo systemctl stop studycity
sudo systemctl disable studycity
sudo rm -rf /etc/systemd/system/studycity.service
