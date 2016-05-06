
        /// <summary>
        /// Timer for the scheduler to push jobs to TBS.
        /// </summary>
        private class SchedulerTimer {

            private long INTERVAL;
            private long timeLimit;

            /// <summary>
            /// Create a new scheduler timer to be used when pushing jobs
            /// to TBS at specifed time intervals. 
            /// </summary>
            /// <param name="TimeIntervalInMillSeconds">
            /// The time interval to push a collection to jobs to TBS.
            /// </param>
            public SchedulerTimer(long TimeIntervalInMillSeconds) {
                INTERVAL = TimeIntervalInMillSeconds;
                timeLimit = CurrentTimeInMilliseconds() + INTERVAL;
            }

            /// <summary>
            /// Determine whether the time to push a collection of scheduled jobs has been reached.
            /// </summary>
            /// <returns>
            /// Whether it is time to push a collection of jobs to TBS. 
            /// </returns>
            public bool TimeReached() {
                return CurrentTimeInMilliseconds() > timeLimit;
            }

            /// <summary>
            /// Restart the timer for the following set of jobs to be scheduled.
            /// </summary>
            public void RestartTimer() {
                timeLimit = CurrentTimeInMilliseconds() + INTERVAL;
            }

            /// <summary>
            /// The current time in Milliseconds. 
            /// </summary>
            /// <returns>Get the current time in Milliseconds</returns>
            private static long CurrentTimeInMilliseconds() {
                return DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
            }
        }
