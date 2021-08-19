#!/bin/bash
sudo systemctl stop studyredis
sudo systemctl disable studyredis
sudo rm -rf /etc/systemd/system/studyredis.service
