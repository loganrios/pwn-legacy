import React from "react";
import WorkNavigation from "./WorkNavigation";

export default {
  title: "WorkNavigation",
  component: WorkNavigation,
  argTypes: {
    onNext: { action: "The Next is the Best" },
    onPrev: { action: "Don't dwell on the past" },
    onChapterSelect: { action: "But the chapter was getting good..." },
    onToggleTrack: { action: "Call the FBI" },
  },
};

const Template = (args) => <WorkNavigation {...args} />;

export const TrackingProg = Template.bind({});
TrackingProg.args = {
  isTracked: true,
  canToggleTrack: true,
  currentChapter: 1,
  chapters: [
    {
      value: "0",
      label: "Chapter 1",
    },
    {
      value: "1",
      label: "Chapter 2",
    },
    {
      value: "2",
      label: "It was the best of times...",
    },
  ],
};

export const NotTracking = Template.bind({});
NotTracking.args = {
  isTracked: false,
  canToggleTrack: true,
  currentChapter: 1,
  chapters: [
    {
      value: "0",
      label: "Chapter 1",
    },
    {
      value: "1",
      label: "Chapter 2",
    },
    {
      value: "2",
      label: "It was the best of times...",
    },
  ],
};

export const GuestView = Template.bind({});
GuestView.args = {
  isTracked: false,
  canToggleTrack: false,
  currentChapter: 1,
  chapters: [
    {
      value: "0",
      label: "Chapter 1",
    },
    {
      value: "1",
      label: "Chapter 2",
    },
    {
      value: "2",
      label: "It was the best of times...",
    },
  ],
};
