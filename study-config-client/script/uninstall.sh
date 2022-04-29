#!/bin/bash
sudo systemctl stop studyconfigclient
sudo systemctl disable studyconfigclient
sudo rm -rf /etc/systemd/system/studyconfigclient.service
