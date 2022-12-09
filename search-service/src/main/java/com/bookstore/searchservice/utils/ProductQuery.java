package com.bookstore.searchservice.utils;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductQuery {
    String _index;
    String _type;
    String _id;
    int _score;

    SourceProduct _source;

}
