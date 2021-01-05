#!/bin/bash
sudo systemctl stop studyeureka
sudo systemctl disable studyeureka
sudo rm -rf /etc/systemd/system/studyeureka.service
