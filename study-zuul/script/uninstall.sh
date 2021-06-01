#!/bin/bash
sudo systemctl stop studyzuul
sudo systemctl disable studyzuul
sudo rm -rf /etc/systemd/system/studyzuul.service
