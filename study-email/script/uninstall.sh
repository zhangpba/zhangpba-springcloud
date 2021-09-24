#!/bin/bash
sudo systemctl stop studyemail
sudo systemctl disable studyemail
sudo rm -rf /etc/systemd/system/studyemail.service
