#!/bin/bash
sudo systemctl stop studystream
sudo systemctl disable studystream
sudo rm -rf /etc/systemd/system/studystream.service
