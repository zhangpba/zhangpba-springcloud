#!/bin/bash
sudo systemctl stop studyzookeeper
sudo systemctl disable studyzookeeper
sudo rm -rf /etc/systemd/system/studyzookeeper.service
