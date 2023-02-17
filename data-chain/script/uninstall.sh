#!/bin/bash
sudo systemctl stop datachain
sudo systemctl disable datachain
sudo rm -rf /etc/systemd/system/datachain.service
