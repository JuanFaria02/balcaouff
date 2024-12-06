package com.balcao.uff.domain.dtos;


public record LoginResponseDTO(String token, UserDto userDto) {
}