package com.enviro.grad001.mduduzijelemduduzi.config;


import com.enviro.grad001.mduduzijelemduduzi.model.Category;
import com.enviro.grad001.mduduzijelemduduzi.model.Guideline;
import com.enviro.grad001.mduduzijelemduduzi.model.Tip;
import com.enviro.grad001.mduduzijelemduduzi.repository.CategoryRepository;
import com.enviro.grad001.mduduzijelemduduzi.repository.GuidelineRepository;
import com.enviro.grad001.mduduzijelemduduzi.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class InitConfig {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GuidelineRepository guidelineRepository;

    @Autowired
    private TipRepository tipRepository;

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {
            // Create and save categories
            Category category1 = new Category("Plastic waste");
            Category category2 = new Category("metal waste");
            categoryRepository.saveAll(Arrays.asList(category1, category2));

            // Create and save guidelines
            Guideline guideline1C1 = new Guideline("Check the recycling symbol: Look for numbers 1-7 in the triangle");
            Guideline guideline2C1 = new Guideline("Rinse containers to remove food residue");
            Guideline guideline3C1 = new Guideline("Remove lids and caps (often recycled separately)");
            guideline1C1.setCategory(category1);
            guideline2C1.setCategory(category1);
            guideline3C1.setCategory(category1);
            Guideline guideline1C2 = new Guideline("Rinse food residue from cans before recycling");
            Guideline guideline2C2 = new Guideline("Remove paper labels when possible");
            Guideline guideline3C2 = new Guideline("Separate ferrous (magnetic) and non-ferrous metals if required");
            guideline1C2.setCategory(category2);
            guideline2C2.setCategory(category2);
            guideline3C2.setCategory(category2);
            guidelineRepository.saveAll(Arrays.asList(guideline1C1, guideline2C1, guideline3C1, guideline1C2, guideline2C2, guideline3C2));

            // Create and save tips
            Tip tip1C1 = new Tip("Reduce use of single-use plastics when possible(tip)");
            tip1C1.setCategory(category1);
            Tip tip1C2 = new Tip("Don't crush aluminum cans (it can interfere with sorting)");
            tip1C2.setCategory(category1);
            tipRepository.saveAll(Arrays.asList(tip1C1, tip1C2));
            System.out.println("Initial data has been added to the database.");
        };
    };
}
