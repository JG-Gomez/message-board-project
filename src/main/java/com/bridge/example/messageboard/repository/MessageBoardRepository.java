package com.bridge.example.messageboard.repository;

import com.bridge.example.messageboard.entity.MBMessage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageBoardRepository extends JpaRepository<MBMessage, Long> {

}
