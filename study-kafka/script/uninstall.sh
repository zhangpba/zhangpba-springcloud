#!/bin/bash
sudo systemctl stop studykafka
sudo systemctl disable studykafka
sudo rm -rf /etc/systemd/system/studykafka.service
