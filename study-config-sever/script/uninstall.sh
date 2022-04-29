#!/bin/bash
sudo systemctl stop studyconfigsever
sudo systemctl disable studyconfigsever
sudo rm -rf /etc/systemd/system/studyconfigsever.service
