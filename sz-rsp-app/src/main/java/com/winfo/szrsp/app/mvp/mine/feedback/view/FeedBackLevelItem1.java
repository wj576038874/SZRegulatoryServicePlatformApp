package com.winfo.szrsp.app.mvp.mine.feedback.view;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by winfo053 on 2018/4/8.
 */

public class FeedBackLevelItem1 implements MultiItemEntity  {

    private String problem;
    private String solution;

    private List<String> problemImgUrlList;
    private List<String> solutionImgUrlList;

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public List<String> getProblemImgUrlList() {
        return problemImgUrlList;
    }

    public void setProblemImgUrlList(List<String> problemImgUrlList) {
        this.problemImgUrlList = problemImgUrlList;
    }

    public List<String> getSolutionImgUrlList() {
        return solutionImgUrlList;
    }

    public void setSolutionImgUrlList(List<String> solutionImgUrlList) {
        this.solutionImgUrlList = solutionImgUrlList;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
