#!/bin/bash
sudo systemctl stop studyuser
sudo systemctl disable studyuser
sudo rm -rf /etc/systemd/system/studyuser.service
