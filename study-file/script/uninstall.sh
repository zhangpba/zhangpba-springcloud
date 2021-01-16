#!/bin/bash
sudo systemctl stop studyfile
sudo systemctl disable studyfile
sudo rm -rf /etc/systemd/system/studyfile.service
